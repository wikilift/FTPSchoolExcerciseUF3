package Domain;

import Data.FtpPojo;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.util.ArrayList;


public interface FTPRepo {
    FTPClient connect(FtpPojo pojo);

    void disconnect(FTPClient ftp);

    void showServerReply(FTPClient ftpClient);


    void listFiles(FTPClient ftp);

    void listFilesPath(FTPClient ftp, String path);

    void uploadFile(FTPClient ftpClient, String filepath, String path);

    public void downloadFile(FTPClient ftpClient, String path,String pathOfNewFile);
}
