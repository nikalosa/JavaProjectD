import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;


public class LinksImages {

	
    public static void main(String[] args) throws IOException {

    	String siteUrl = "http://stackoverflow.com/questions/6159118/using-java-to-pull-data-from-a-webpage";
        URL url = new URL(siteUrl);

        URLConnection con = url.openConnection();
        InputStream is =con.getInputStream();

        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = "";
        String htmlString = "";
        
        ArrayList < String > linkList = new  ArrayList < String >();

        ArrayList < String > imageList = new  ArrayList < String >();
        
        while ((line = br.readLine()) != null) {
            htmlString+=line;
            htmlString+='\n';
        }
        String site = "href=";
        String img = "src=";
        for(int i=0;i<htmlString.length();i++){
        	boolean isLink=true;
        	boolean isImage=true;
        	for(int j=0;j<site.length() && i+j<htmlString.length();j++){
        		if(htmlString.charAt(i+j)!=site.charAt(j)){
        			isLink=false;
        			break;
        		}
        	}
        	if(isLink){
        		i=i+(int)site.length()+1;
        		
        		String result="";
        		while(htmlString.charAt(i)!='\"'){
        			result+=htmlString.charAt(i);
        			i++;
        		}
        		linkList.add(result);
        		continue;
        	}
        	for(int j=0;j<img.length() && i+j<htmlString.length();j++){
        		if(htmlString.charAt(i+j)!=img.charAt(j)){
        			isImage=false;
        			break;
        		}
        	}
        	if(isImage){
        		i+=(int)img.length()+1;
        		String result="";
        		while(htmlString.charAt(i)!='\"'){
        			result+=htmlString.charAt(i);
        			i++;
        		}
        		imageList.add(result);
        	}
        }
        System.out.println("Site linkgs:");
        for(int i=0;i<linkList.size();i++){
        	System.out.println(linkList.get(i));
        }
        System.out.println("Images:");
        for(int i=0;i<imageList.size();i++){
        	System.out.println(imageList.get(i));
        }
    }
}