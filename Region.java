public class Region implements Comparable<Region> {
	
    String chrom;
    int start, end;
	
	
    public Region( ) {
    }

    public Region( String chrom, int  start, int end ) {
	this.chrom = chrom;
	this.start = start;
	this.end   = end;
    }
	
    public int length() {
	return end - start  + 1 ;
    }

    public boolean equals( Region region ) {
	if ( region.chrom.equals( this.chrom) &&
	     region.start == this.start &&
	     region.end   == this.end )
	    return true;

	return false;
    }


    public int compare_chroms( String c1, String c2) {

	for(int pos = 0;; pos++) {

	    //System.out.println( String.format( "Position %d", pos));
	    if (c1.length() <= pos)
		return -1;

	    if (c2.length() <= pos)
		return 1;

            char c1_char = c1.charAt(pos);
            char c2_char = c2.charAt(pos);
	    //System.out.println( String.format( "%c -- %c", c1_char, c2_char));

	    if (c1_char ==  c2_char )
		continue;
	    
	    if ( !Character.isDigit( c1_char )  && !Character.isDigit( c2_char ) ) {
		if ( c1_char ==  c2_char )
		    return 0;

		if ( c1_char <  c2_char )
		    return -1;

		return 1;
		
	    }
	    else if ( !Character.isDigit( c1_char ) ) {
		return 1;
	    }
	    else if ( !Character.isDigit( c2_char ) ) {
		return -1;
	    }

	}


    }



    public int compareTo( Region region ) {


	if ( this.equals( region ))
	    return 0;

	if( region.chrom.compareTo(this.chrom) != 0)
	    return (this.compare_chroms(this.chrom, region.chrom));
	//return ( this.chrom.compareTo(region.chrom));
	//return ( region.chrom.compareTo(this.chrom));


	
	if( region.start < this.start ||
	    region.end   < this.end )
	    return 1;
		     
	return -1;
    }
    
    public boolean equalsIgnoreCase( String region) {
	return this.toString().equalsIgnoreCase( region );
    }

    public boolean equalsChrom( String chrom) {
	return this.chrom.equalsIgnoreCase( chrom);
    }

    
    @Override
    public String toString() {
        return String.format("%s:%d-%d", this.chrom, this.start, this.end);
    }
    
}
