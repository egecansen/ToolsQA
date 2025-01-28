package steps;

import io.cucumber.java.en.Given;
import pages.DownloadAndUploadPage;
import java.io.File;

public class DownloadAndUploadPageSteps {

    DownloadAndUploadPage downloadAndUploadPage = new DownloadAndUploadPage();

    @Given("Click on download button from the selected section")
    public void clickDownloadButton() {
        downloadAndUploadPage.log.new Info("Downloading the file");
        downloadAndUploadPage.clickElementUntil(downloadAndUploadPage.downloadButton, true);
        String downloadsDirectory = new File("src/test/resources/files/downloads").getAbsolutePath();
        downloadAndUploadPage.waitUntilDownloaded(downloadsDirectory, "sampleFile");
    }

    @Given("Upload the downloaded file from downloads folder and clear directory")
    public void uploadFile() {
        String downloadsDirectory = new File("src/test/resources/files/downloads").getAbsolutePath();
        String chosenFile = downloadAndUploadPage.getLastModified(downloadsDirectory).getAbsolutePath();
        downloadAndUploadPage.log.new Info("Uploading...");
        downloadAndUploadPage.uploadBox.sendKeys(chosenFile);
        downloadAndUploadPage.cleanDirectory(downloadsDirectory);
    }

}
