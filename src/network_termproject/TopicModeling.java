package network_termproject;


import cc.mallet.types.*;
import cc.mallet.pipe.*;
import cc.mallet.pipe.iterator.*;
import cc.mallet.topics.*;
import cc.mallet.util.Randoms;

import java.util.*;
import java.util.regex.*;
import java.io.*;
/*
 * muchin running that topicmodeling use mallet open source liblary
 */
public class TopicModeling {    
	String[] a = new String[30];
    String[] topic1 = new String[7];
    String[] topic2 = new String[7];
    String[] topic3 = new String[7];
    String[] topic4 = new String[7];
    String[] topic5 = new String[7];
    String[] topic6 = new String[7];
    public void a() throws Exception
    {
    	String dataFolderPath = "C:\\software\\problem";
    	String stopListFilePath = "stoplists/en.txt";

        ArrayList<Pipe> pipeList = new ArrayList<Pipe>();
        pipeList.add(new Input2CharSequence("UTF-8"));
        Pattern tokenPattern = Pattern.compile("[\\p{L}\\p{N}_]+");
        pipeList.add(new CharSequence2TokenSequence(tokenPattern));
        pipeList.add(new TokenSequenceLowercase());
        pipeList.add(new TokenSequenceRemoveStopwords(new File(stopListFilePath), "utf-8", false, false, false));
        pipeList.add(new TokenSequence2FeatureSequence());
        pipeList.add(new Target2Label());
        SerialPipes pipeline = new SerialPipes(pipeList);

        FileIterator folderIterator = new FileIterator(
        			new File[] {new File(dataFolderPath)},
                    new TxtFilter(),
                    FileIterator.LAST_DIRECTORY);
        // Construct a new instance list, passing it the pipe
        //  we want to use to process instances.
        InstanceList instances = new InstanceList(pipeline);

        instances.addThruPipe(folderIterator);

        int numTopics = 6;
        ParallelTopicModel model = new ParallelTopicModel(numTopics, 0.01, 0.01);

        model.addInstances(instances);

        model.setNumThreads(5);

        model.setNumIterations(50);
        model.estimate();
        
        //Saving model
		String modelPath = "myTopicModel";
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream (new File(modelPath+".model")));
		oos.writeObject(model);
		oos.close();     
		oos = new ObjectOutputStream(new FileOutputStream (new File(modelPath+".pipeline")));
		oos.writeObject(pipeline);
		oos.close();     
		
		ObjectInputStream ois = new ObjectInputStream (new FileInputStream (new File(modelPath+".model")));
		model = (ParallelTopicModel) ois.readObject();
		ois.close();   
		ois = new ObjectInputStream (new FileInputStream (new File(modelPath+".pipeline")));
		pipeline = (SerialPipes) ois.readObject();
		ois.close();   
		//Loading the model
        Alphabet dataAlphabet = instances.getDataAlphabet();
        
        FeatureSequence tokens = (FeatureSequence) model.getData().get(0).instance.getData();
        LabelSequence topics = model.getData().get(0).topicSequence;
        
        Formatter out = new Formatter(new StringBuilder(), Locale.US);
        for (int position = 0; position < tokens.getLength(); position++) {
            out.format("%s-%d ", dataAlphabet.lookupObject(tokens.getIndexAtPosition(position)), topics.getIndexAtPosition(position));
        }
        double[] topicDistribution = model.getTopicProbabilities(0);

        // Get an array of sorted sets of word ID/count pairs
        ArrayList<TreeSet<IDSorter>> topicSortedWords = model.getSortedWords();
        
        // Show top 5 words in topics with proportions for the first document
        int k=0;
        for (int topic = 0; topic < numTopics; topic++) {
            Iterator<IDSorter> iterator = topicSortedWords.get(topic).iterator();
            
            out = new Formatter(new StringBuilder(), Locale.US);
            out.format("%d\t%.3f\t", topic, topicDistribution[topic]);
            int rank = 0;
            while (iterator.hasNext() && rank < 5) {
                IDSorter idCountPair = iterator.next();
                a[k]=(String) dataAlphabet.lookupObject(idCountPair.getID());
                k++;
                rank++;
            }
        }
        //match each set to certain topic
        k=1;
        int k1=1;
        int k2=1;
        int k3=1;
        int k4=1;
        int k5=1;
        for(int i=0;i<30;i++)
        {
        	if(i<5)
        	{
        		topic1[k]=a[i];
        		k++;
        	}
        	if(i>=5&&i<10)
        	{
        		topic2[k1]=a[i];
        		k1++;
        	}
        	if(i>=10&&i<15)
        	{
        		topic3[k2]=a[i];
        		k2++;
        	}
        	if(i>=15&&i<20)
        	{
        		topic4[k3]=a[i];
        		k3++;
        	}
        	if(i>=20&&i<25)
        	{
        		topic5[k4]=a[i];
        		k4++;
        	}
        	if(i>=25&&i<30)
        	{
        		topic6[k5]=a[i];
        		k5++;
        	}
        }
        topic1[0]="null/null";
        topic2[0]="null/null";
        topic3[0]="null/null";
        topic4[0]="null/null";
        topic5[0]="null/null";
        topic6[0]="null/null";
        for(int i=1;i<6;i++)
        {
        	if(topic1[i].equals("dns"))
        	{
        		topic1[0]="network/application";        		
        	}
        	if(topic1[i].equals("tcp"))
        	{
        		topic1[0]="network/transport";
        	}
        	if(topic1[i].equals("delays"))
        	{
        		topic1[0]="network/introduction";
        	}
        	if(topic1[i].equals("segment"))
        	{
        		topic1[0]="network/link";
        	}
        	if(topic1[i].equals("dhcp"))
        	{
        		topic1[0]="network/network";
        	}
        	if(topic1[i].equals("cts"))
        	{
        		topic1[0]="network/wireless";
        	}
        	
        	if(topic2[i].equals("dns"))
        	{
        		topic2[0]="network/application";        		
        	}
        	if(topic2[i].equals("tcp"))
        	{
        		topic2[0]="network/transport";
        	}
        	if(topic2[i].equals("delay"))
        	{
        		topic2[0]="network/introduction";
        	}
        	if(topic2[i].equals("segment"))
        	{
        		topic2[0]="network/link";
        	}
        	if(topic2[i].equals("dhcp"))
        	{
        		topic2[0]="network/network";
        	}
        	if(topic2[i].equals("cts"))
        	{
        		topic2[0]="network/wireless";
        	}
        	
        	if(topic3[i].equals("dns"))
        	{
        		topic3[0]="network/application";        		
        	}
        	if(topic3[i].equals("tcp"))
        	{
        		topic3[0]="network/transport";
        	}
        	if(topic3[i].equals("delay"))
        	{
        		topic3[0]="network/introduction";
        	}
        	if(topic3[i].equals("segment"))
        	{
        		topic3[0]="network/link";
        	}
        	if(topic3[i].equals("dhcp"))
        	{
        		topic3[0]="network/network";
        	}
        	if(topic3[i].equals("cts"))
        	{
        		topic3[0]="network/wireless";
        	}
        	
        	if(topic4[i].equals("dns"))
        	{
        		topic4[0]="network/application";        		
        	}
        	if(topic4[i].equals("tcp"))
        	{
        		topic4[0]="network/transport";
        	}
        	if(topic4[i].equals("delay"))
        	{
        		topic4[0]="network/introduction";
        	}
        	if(topic4[i].equals("segment"))
        	{
        		topic4[0]="network/link";
        	}
        	if(topic4[i].equals("dhcp"))
        	{
        		topic4[0]="network/network";
        	}
        	if(topic4[i].equals("cts"))
        	{
        		topic4[0]="network/wireless";
        	}
        	
        	if(topic5[i].equals("dns"))
        	{
        		topic5[0]="network/application";        		
        	}
        	if(topic5[i].equals("tcp"))
        	{
        		topic5[0]="network/transport";
        	}
        	if(topic5[i].equals("delay"))
        	{
        		topic5[0]="network/introduction";
        	}
        	if(topic5[i].equals("segment"))
        	{
        		topic5[0]="network/link";
        	}
        	if(topic5[i].equals("dhcp"))
        	{
        		topic5[0]="network/network";
        	}
        	if(topic5[i].equals("cts"))
        	{
        		topic5[0]="network/wireless";
        	}
        	
        	if(topic6[i].equals("dns"))
        	{
        		topic6[0]="network/application";        		
        	}
        	if(topic6[i].equals("tcp"))
        	{
        		topic6[0]="network/transport";
        	}
        	if(topic6[i].equals("delay"))
        	{
        		topic6[0]="network/introduction";
        	}
        	if(topic6[i].equals("segment"))
        	{
        		topic6[0]="network/link";
        	}
        	if(topic6[i].equals("dhcp"))
        	{
        		topic6[0]="network/network";
        	}
        	if(topic6[i].equals("cts"))
        	{
        		topic6[0]="network/wireless";
        	}
        }
        System.out.println(topic1[0]);
        System.out.println(topic2[0]);
        System.out.println(topic3[0]);
        System.out.println(topic4[0]);
        System.out.println(topic5[0]);
        System.out.println(topic6[0]);
    }
    public String[] get1()
    {
    	return topic1;
    }
    public String[] get2()
    {
    	return topic2;
    }
    public String[] get3()
    {
    	return topic3;
    }
    public String[] get4()
    {
    	return topic4;
    }
    public String[] get5()
    {
    	return topic5;
    }
    public String[] get6()
    {
    	return topic6;
    }
}

class TxtFilter implements FileFilter {

    public boolean accept(File file) {
        return file.toString().endsWith(".txt");
    }
}