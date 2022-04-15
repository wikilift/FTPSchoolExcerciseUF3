package Data;

public class FtpPojo {
    private String serverFTP,serverUser,serverPassword;
    private int port;

    public FtpPojo(String serverFTP, String serverUser, String serverPassword,int port) {
        this.serverFTP = serverFTP;
        this.serverUser = serverUser;
        this.serverPassword = serverPassword;
        this.port=port;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getServerFTP() {
        return serverFTP;
    }

    public void setServerFTP(String serverFTP) {
        this.serverFTP = serverFTP;
    }

    public String getServerUser() {
        return serverUser;
    }

    public void setServerUser(String serverUser) {
        this.serverUser = serverUser;
    }

    public String getServerPassword() {
        return serverPassword;
    }

    public void setServerPassword(String serverPassword) {
        this.serverPassword = serverPassword;
    }
}
