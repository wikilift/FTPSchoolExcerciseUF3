import Data.FtpPojo;
import Domain.FTPRepo;
import Domain.FTPRepoImpl;
import org.apache.commons.net.ftp.FTPClient;

public class Main {
    public static void main(String[] args) {

        //Store data from ftp
        FtpPojo myPojo=new FtpPojo("demo.wftpserver.com","demo","demo",21);

        FTPRepo repo=new FTPRepoImpl();
        //connect to server
        FTPClient client=repo.connect(myPojo);
        //list files on server
        repo.listFiles(client);
        //list files on /download
        repo.listFilesPath(client,"/download");
        //download file archivo.doc
        repo.downloadFile(client,"/upload/archivo.doc","archivo2.doc");
        //upload file
        repo.uploadFile(client,"archivo2.doc","/upload/archivo2.doc");
        //disconect from server
        repo.disconnect(client);
    }
}
