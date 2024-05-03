public class File {
    private String filename;
    private double downloadPercent;
    private boolean downloadStatus;

    private double fileSize;

    private  double downloaded;
    public File(String filename, double fileSize) {
        this.filename = filename;
        this.downloadPercent = 0.00;
        this.downloadStatus = false;
        this.fileSize = fileSize;
        this.downloaded = 0;
    }

    public String getFilename() {
        return filename;
    }

    public double getDownloadPercent() {
        return downloadPercent;
    }

    public boolean isDownloadStatus() {
        return downloadStatus;
    }

    public double getFileSize()
    {
        return this.fileSize;
    }
    public void incrementDownloadPercentage(int added)
    {
        downloaded+=added;
        if(downloaded >= fileSize)
        {
            this.downloadPercent = 100;
            this.downloaded = this.fileSize;
            this.downloadStatus = true;
        }
        else {
            downloadPercent = (downloaded/fileSize)*100;
        }
    }
}
