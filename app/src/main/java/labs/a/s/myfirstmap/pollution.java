package labs.a.s.myfirstmap;

public class pollution
{

    private String mtext;

    private String mdescription;


    public pollution(String text,  String description)
    {

        mtext=text;
        mdescription=description;

    }

    public String gettext(){
        return mtext;
    }
    public String getdescription(){
        return mdescription;
    }

}

