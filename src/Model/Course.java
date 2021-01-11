/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author ADITYA N SAH
 */
public class Course {
    public int Cid;
    public String Cname;
    public String Iname;
    public String Iphone;
    public String Btitle;
    public String Bauthor;
    public String Bpublisher;
    public Course(int Cid, String Cname, String Iname, String Iphone, String Btitle, String Bauthor, String Bpublisher){
        this.Cid = Cid;
        this.Cname = Cname;
        this.Iname = Iname;
        this.Iphone = Iphone;
        this.Btitle = Btitle;
        this.Bauthor = Bauthor;
        this.Bpublisher = Bpublisher;
    }
    
}
