import java.io.File;
import java.util.ArrayList;
import java.util.Properties;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Address;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeBodyPart;

/*
 * This Program reads unread email from Gmail Inbox
 *
 * Note : Specify Username and password through command line.
 * */


public class ReadEmail_2
{
    public static final Pattern MY_PATTERN = Pattern.compile("\\+(.*?)\\@");
    
    public interface MimeBodyPartHandler
        {
            void handleMimeBodyPart(MimeBodyPart mimeBodyPart);
        }
    
    class Test_bodyPart implements MimeBodyPartHandler
    {
		@Override
		public void handleMimeBodyPart(MimeBodyPart mimeBodyPart)
		{
            String attachFiles="";
            String saveDirectory = "/Users/akshaypitale/Documents";
            String fileName;
			try
			{
				fileName = mimeBodyPart.getFileName();
				attachFiles += fileName + ", ";
				mimeBodyPart.saveFile(saveDirectory + File.separator + fileName);
				System.out.println("Attachments: " + attachFiles);
			}
			catch (Exception e) {e.printStackTrace();}
		}
        
    }
    
    public void readEmailAttachment(String host,String userName, String password)
    {
		Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");
        try {
            Session session = Session.getInstance(props, null);
            Store store = session.getStore();
            store.connect(host, userName,password);
            Test_bodyPart bodyPart=new Test_bodyPart();
            findAttachments(store, "INBOX", "check email content in java", bodyPart, "FieldLens");
        } catch (Exception mex) { mex.printStackTrace();}
	}
    
	private void findAttachments(Store store, String folderName, String subjectContent,
                                 MimeBodyPartHandler callbackInterface, String newLabel) throws MessagingException,Exception
	{
		Folder inbox = store.getFolder(folderName);
		inbox.open(Folder.READ_WRITE);
		
		Folder newFolder = store.getFolder(newLabel);
		if (newFolder == null || !newFolder.exists())
            newFolder.create(Folder.HOLDS_MESSAGES);
		newFolder.open(Folder.READ_WRITE);
		
		Message[] arrayMessages = inbox.getMessages();
		arrayMessages = newFolder.getMessages();
		ArrayList<Message> filterMsgs=new ArrayList<Message>();
		
		for (int i = 0; i < arrayMessages.length; i++)
		{
			String subject = arrayMessages[i].getSubject();
			if(subject!=null && subject.contains(subjectContent))
			{
                filterMsgs.add(arrayMessages[i]);
                Message message =arrayMessages[i];
                writePart(message, callbackInterface);
			}
			else
			{
				System.out.println("There is no email with subject: " + subjectContent);
			}
		}
        
		if(newFolder!=null && filterMsgs.size()!=0)
		{
			Message[] filterArray = filterMsgs.toArray(new Message[filterMsgs.size()]);
			inbox.copyMessages(filterArray,newFolder);
			
            for ( int i = 0; i < filterArray.length; i++)
            {
                filterArray[i].setFlag(Flags.Flag.DELETED, true);
            }
            
            inbox.expunge();
		}
	}
    
    private  void writePart(Part p, MimeBodyPartHandler callbackInterface) throws Exception
    {
        if (p instanceof Message)
            writeEnvelope((Message) p);
        if (p.isMimeType("text/plain"))
        {
            System.out.println("CONTENT:");
            System.out.println((String) p.getContent());
        }
        else if (p.isMimeType("multipart/*"))
        {
            Multipart mp = (Multipart) p.getContent();
            int count = mp.getCount();
            for (int i = 0; i < count; i++)
	            writePart(mp.getBodyPart(i),callbackInterface);
        }
        else if (Part.ATTACHMENT.equalsIgnoreCase(p.getDisposition()))
        {
            MimeBodyPart mimeBodyPart = (MimeBodyPart) p;
            callbackInterface.handleMimeBodyPart(mimeBodyPart);
        }
    }
    
    private  void writeEnvelope(Message m) throws Exception
    {
        System.out.println("This is the message envelope");
        System.out.println("---------------------------");
        Address[] a;
        // FROM
        if ((a = m.getFrom()) != null)
        {
            for (int j = 0; j < a.length; j++)
                System.out.println("FROM: " + a[j].toString());
        }
        // TO
        if ((a = m.getRecipients(Message.RecipientType.TO)) != null)
        {
            for (int j = 0; j < a.length; j++)
            {
                String s=a[j].toString();
                System.out.println("TO: " +s);
                if(s.contains("+")){
     				Matcher matcher = MY_PATTERN.matcher(s);
                    while(matcher.find())
                        System.out.println("Sub-String:" + matcher.group(1));
     			}
            }
        }
        // SUBJECT
        if (m.getSubject() != null)
            System.out.println("SUBJECT: " + m.getSubject());
        
        System.out.println("---------------------------");
    }
	
    public static void main(String[] args)
    {
        String host = "imap.gmail.com";
        if(args.length==0)
        {
            System.out.print("Please Add Username And Password through command line");
            System.exit(0);
        }
        String userName = args[0];
        String password = args[1];
        ReadEmail_2 email = new ReadEmail_2();
        email.readEmailAttachment(host, userName, password);
    }
    
	private static void log(Object obj) {
		System.out.println( String.valueOf(obj) );		
	}
}

