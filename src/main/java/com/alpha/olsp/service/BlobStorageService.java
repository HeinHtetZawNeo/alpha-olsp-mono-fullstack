package com.alpha.olsp.service;

import com.azure.storage.blob.BlobContainerClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BlobStorageService {
    BlobContainerClient getBlobContainerClient();

    List<String> uploadFiles(MultipartFile[] files) throws IOException;

    List<String> storeFilesInAzureBlob(MultipartFile[] files);
}
