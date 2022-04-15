package Domain;

import Data.FtpPojo;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class FTPRepoImpl implements FTPRepo {
    @Override
    public FTPClient connect(FtpPojo pojo) {
        FTPClient client = new FTPClient();
        try {
            client.connect(pojo.getServerFTP(), pojo.getPort());
            showServerReply(client);
            int replyCode = client.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                System.out.println("Operation failed. Server reply code: " + replyCode);
                return null;
            }
            boolean success = false;
            success = client.login(pojo.getServerUser(), pojo.getServerPassword());
            showServerReply(client);
            if (!success) {
                System.out.println("Could not login to the server");
                return null;
            } else {
                System.out.println("LOGGED IN SERVER");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return client;
    }

    @Override
    public void disconnect(FTPClient ftpClient) {
        try {
            if (ftpClient.isConnected()) {
                ftpClient.logout();
                ftpClient.disconnect();
                System.out.println("Disconnected from the server correctly");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void showServerReply(FTPClient ftpClient) {
        String[] replies = ftpClient.getReplyStrings();
        if (replies != null && replies.length > 0) {
            for (String aReply : replies) {
                System.out.println("SERVER: " + aReply);
            }
        }
    }


    @Override
    public void listFiles(FTPClient ftp) {
        FTPFile[] list = null;
        try {
            list = ftp.listFiles();

            DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

            for (FTPFile file : list) {
                String details = file.getName();
                if (file.isDirectory()) {
                    details = "[" + details + "]";
                }
                details += "\t\t" + file.getSize();
                details += "\t\t" + dateFormatter.format(file.getTimestamp().getTime());
                System.out.println(details);

            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void listFilesPath(FTPClient ftp, String path) {
        FTPFile[] list = null;
        try {
            list = ftp.listFiles(File.separator + path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        for (FTPFile file : list) {
            String details = file.getName();
            if (file.isDirectory()) {
                details = "[" + details + "]";
            }
            details += "\t\t" + file.getSize();
            details += "\t\t" + dateFormatter.format(file.getTimestamp().getTime());
            System.out.println(details);

        }

    }

    @Override
    public void uploadFile(FTPClient ftpClient, String filepath, String path) {


        try {
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);



            File file = new File(filepath);

            InputStream inputStream = new FileInputStream(file);


            boolean done = ftpClient.storeFile(path, inputStream);
            inputStream.close();
            if (done) {
                System.out.println("The  file is uploaded successfully.");
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void downloadFile(FTPClient ftpClient,String path,String pathOfNewFile) {
        ftpClient.enterLocalPassiveMode();
        try {
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            boolean success=false;


            File file = new File(pathOfNewFile);
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
            InputStream inputStream = ftpClient.retrieveFileStream(path);
            byte[] bytesArray = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(bytesArray)) != -1) {
                outputStream.write(bytesArray, 0, bytesRead);
            }

            success = ftpClient.completePendingCommand();
            if (success) {
                System.out.println("File has been downloaded successfully.");
            }else{
                System.out.println("File wasn't downloaded.");
            }
            outputStream.close();
            inputStream.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


