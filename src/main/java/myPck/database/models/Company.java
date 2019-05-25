package myPck.database.models;

import java.io.*;

public class Company implements Serializable {
    String name;
    String address;
    String nip;

    public Company(String name, String address, String nip) {
        this.name = name;
        this.address = address;
        this.nip = nip;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getNip() {
        return nip;
    }

    public void writeToFile(String path) throws IOException {
        FileOutputStream f = new FileOutputStream(new File(path));
        ObjectOutputStream o = new ObjectOutputStream(f);

        // Write objects to file
        o.writeObject(this);

        o.close();
        f.close();
    }
    public static Company readObjectFromFile(String path) throws IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream(new File(path));
        ObjectInputStream oi = new ObjectInputStream(fi);

        // Read objects
        Company company = (Company) oi.readObject();

        oi.close();
        fi.close();
        return company;
    }
}
