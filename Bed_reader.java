import java.util.*;
import java.io.*;
import java.util.regex.*;
import java.util.stream.Collectors;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Bed_reader {

    String filename;
    Pattern BED_PATTERN = Pattern.compile("^(\\w+)\\t(\\d+)\\t(\\d+)");


    public Bed_reader( String filename ) {

	ArrayList<Region> region_list = new ArrayList<Region>();


	
	
	try (Stream<String> stream = Files.lines(Paths.get( filename ))) {

	    //1. filter line 3
	    //2. convert all content to upper case
	    //3. convert it into a List
	    stream.map(String::toLowerCase)
		  .forEach( line -> { Matcher m = BED_PATTERN.matcher(line);
			  if (m.find( )) {
			      System.out.println("0 Found value: " + m.group(0) );
			      System.out.println("1 Found value: " + m.group(1) );
			      System.out.println("2 Found value: " + m.group(2) );
			      System.out.println("3 Found value: " + m.group(3) );
			      Region region = new Region( m.group(1),
							  Integer.parseInt(m.group(2)),
							  Integer.parseInt(m.group(3)));
			      region_list.add( region );
				  
			  }
		    });
	    //.collect(Collectors.toList());

	    System.exit( 1 );
	} catch (IOException e) {
	    e.printStackTrace();
	}



	

    }

    
}
