package com.alpha.olsp.service.Impl;

import com.alpha.olsp.service.BlobStorageService;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BlobStorageServiceImpl implements BlobStorageService {
    private final Dotenv dotenv = Dotenv.load();
    private final String containerName = "product-image-container";
    private final String accountName = dotenv.get("AZURE_STORAGE_ACCOUNT_NAME");
    private final String accountKey = dotenv.get("AZURE_STORAGE_ACCOUNT_KEY");
    private final String endpoint = dotenv.get("AZURE_STORAGE_BLOB_ENDPOINT");

    @Override
    public BlobContainerClient getBlobContainerClient() {
        // Create a BlobServiceClient
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .endpoint(endpoint)
                .credential(new com.azure.storage.common.StorageSharedKeyCredential(accountName, accountKey))
                .buildClient();

        // Get the container client
        return blobServiceClient.getBlobContainerClient(containerName);
    }

    @Override
    public List<String> uploadFiles(MultipartFile[] files) throws IOException {
        BlobContainerClient containerClient = getBlobContainerClient();
        List<String> fileUrls = new ArrayList<>();

        for (MultipartFile file : files) {
            // Generate a unique filename with UUID and file extension
            String originalFilename = file.getOriginalFilename();
            String extension = "";

            if (originalFilename != null && originalFilename.contains(".")) {
                extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }

            String fileName = UUID.randomUUID() + extension;

            BlobClient blobClient = containerClient.getBlobClient(fileName);

            blobClient.upload(file.getInputStream(), file.getSize(), true);
            fileUrls.add(blobClient.getBlobUrl());
        }

        return fileUrls;
    }

    @Override
    public List<String> storeFilesInAzureBlob(MultipartFile[] files) {
        try {
            return uploadFiles(files); // Reuse uploadFiles method
        } catch (IOException e) {
            throw new RuntimeException("Error uploading files to Azure Blob Storage", e);
        }
    }
}