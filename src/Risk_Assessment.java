// STUDENT VERSION

// Risk Assessment.java
// Name: Matthew Twigg
// Date: 2/24/21

/* Idea (from article): each inmate is assigned a true probability of 
   reoffending based on normal distribution mean and std dev from the 
   Ohio data - weighted avg = 21.79, std dev = 13.85 then the computer 
   uses those probabilities to decide whether each dot will actually 
   reoffend a dot with a 75% risk will on average reoffend 3/4 times no 
   one knows the true chance of reoffending so risk assessment tries to 
   estimate it normal distribution with mean set at true risk and std 
   dev 0.15 can use random.gauss(mu, sigma) for normal distribution
*/

import java.util.*;

public class Risk_Assessment{
    public static double lowCutOff = 0;
    public static double mediumCutOff = 0;
    public static double highCutOff = 43;
    public static Defendant[] defendants;
    public static ArrayList<Defendant> awardedParole = new ArrayList<Defendant>();
	public static ArrayList<Defendant> deniedParole = new ArrayList<Defendant>();
    
    public static void printDefendantList(Defendant[] l){
	// given an array of defendants, print out each defendant
    	Defendant[] defendants = new Defendant[100];
    	for (int i = 0; i<defendants.length; i++) {
    		System.out.println(defendants[i]);
    	}
	// to help debugging

  
    }

    public static Defendant[] createDefendants(){
	// outputs an array of 100 defendants with "true probability of recidivism
	// from a normal distribution based on the Ohio Data and a willReoffend based
	// on the trueRisk
    	Random rand = new Random(); //defining random variable
    	Defendant[] defendants = new Defendant[100];//defining array which all the defendants will be stored in
    	for (int i = 0; i<defendants.length; i++) { //looping through the array with all the defendants
    		double trueRisk = (((rand.nextGaussian()) * 13.85) + 21.79); //calculating the trueRisk score
    		
    		//Line 49 - 58  makes sure that the true risk score is within the range 0-43, if its not the while loops will keep looping till a number that falls in that range is generated
    		if (trueRisk >= 43 ) { 
    			while(trueRisk >= 43) {
    				trueRisk = (((rand.nextGaussian()) * 13.85) + 21.79);
    			}
    		}
    		if (trueRisk <= 0 ) {
    			while(trueRisk <= 0) {
    				trueRisk = (((rand.nextGaussian()) * 13.85) +  21.79);
    			}
    		}
    		
    		// lines 60 - 61 are calculating the the trueRecidivism score
    		double trueRecidivism = rand.nextDouble();
    		trueRecidivism = trueRecidivism * 43;
    		
    		boolean willReoffend; //creating the willReoffend variable
    		
    		//the rest of the method assigns the defendant a bool value to the Willreoffend variable, and also assigns the truerisk score
    		if (trueRecidivism > trueRisk) {
    			willReoffend = false;
    		} else {
    			willReoffend = true;
    		}
    		defendants[i] = new Defendant(trueRisk, willReoffend);
    	}
    	return defendants;
    	
    }

    public static double[] chooseCutOffs(){
	// output the double cutoff for low, med, and high risk categories
	// taken from the user 
    	
    //this first block (lines 83 - 88) creates the scanner and takes input and assigns values for lowCutOff and mediumCutOff, while also using while loops for input validation 
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Enter the low cut off percentage (a number between 1 and 98): ");
    	lowCutOff = scanner.nextDouble();
    	if (lowCutOff < 1) {
    		while(lowCutOff < 1) {
    			System.out.println("You Entered a number outside the range. Enter the low cut off percentage (a number between 1 and 98): ");
    	    	lowCutOff = scanner.nextDouble();
			}
    	}
    	double maxMed =lowCutOff+1;
    	System.out.println("Enter the medium cut off percentage (a number between "+ maxMed + " and 98):" );
    	mediumCutOff = scanner.nextDouble();
    	if (mediumCutOff< maxMed) {
    		while(mediumCutOff < maxMed) {
    			System.out.println("You Entered a number outside the range. Enter the low cut off percentage (a number between 1 and 98): ");
    	    	mediumCutOff = scanner.nextDouble();
			}
    	}
    	
    //this second block (lines 91 - 95) converts the values assigned by the users input for lowCutOff and mediumCutOff to percentages out of 43
    	highCutOff = 100;
    	lowCutOff = lowCutOff/100;
    	lowCutOff = 43 * lowCutOff;
    	mediumCutOff = mediumCutOff/100;
    	mediumCutOff = 43 * mediumCutOff;
    	
    //this third block (lines 98 - 101) organizes the cutOffs into tuple, so it can all be returned in one statement
    	double[] cutOffs = new double[3];
    	cutOffs [0] = lowCutOff;
    	cutOffs [1] = mediumCutOff;
    	cutOffs [2] = highCutOff;
    	
    	return cutOffs;
    	
    }

    public static void assessDefendants(){
	// assess and decide parole for each defendant
    
   //intializing variables
    	double assessmentScore;
    	String riskCategory;
    	boolean givenParole;
    //for loop has the defendants take a risk assessment, assigns a riskcategory and a decides their parole. If the defendanted is granted parole, they are added to the "awarded parole" arraylist if they are denied parole they are added to the "denied parole" array list
    	for (int i = 0; i<defendants.length; i++) {
    		defendants[i].assess(lowCutOff, mediumCutOff, highCutOff);
    		assessmentScore = defendants[i].getAssessmentScore();
    		//System.out.println(n);
    		riskCategory = defendants[i].getRiskCategory();
    		//System.out.println(z);
    		defendants[i].decideParole();
    		givenParole = defendants[i].isGivenParole();
    		//System.out.println(y);
    		if(givenParole == true) {
    			awardedParole.add(defendants[i]);
    		}
    		if(givenParole == false) {
    			deniedParole.add(defendants[i]);
    		}
    		
    	}
 
    }
    

    public static void paroleStats(){
	// calculate and output the appropriate parole statics demonstrated in sample
    	
    	// defining all the variables need
    	double aParole = awardedParole.size(); //aParole and dParole store the size of the array lists 
    	double dParole = deniedParole.size();
    	double numADefs = 0;
    	double numDDefs = 0;
    	boolean offendAgain;
    	boolean offendAgain2;
    	double percent1;
    	double percent2;
    	
    	//the two forloops loop through array defendants, and then check if they they will offend again, if they do they are checked to see if they are in either aParole or dParole, offendAgain or OffendAgain will add a value of 1 to its current value.
    	for (int i = 0; i<defendants.length; i++) {
    		offendAgain = defendants[i].willOffendAgain();
    		if(offendAgain == true) {
    			if(awardedParole.contains(defendants[i]) == true) {
    				numADefs++;
    			}
    		}
    	}
	
    	for (int i = 0; i<defendants.length; i++) {
    		offendAgain2 = defendants[i].willOffendAgain();
    		if(offendAgain2 == false) {
    			if(deniedParole.contains(defendants[i]) == true) {
    				numDDefs++;
    			}
    		}
    	}
    	
    	//calculates the percentages and rounds it to the nearest tenth
    	percent1 = (numADefs/aParole) * 100;
    	double percent1R = Math.round(percent1 * 100.0) / 100.0;
    	percent2 = (numDDefs/dParole) * 100;
    	double percent2R = Math.round(percent2 * 100.0) / 100.0;
    	
	
    	
    	//prints out all the stats
    	System.out.println("the number of defendants awarded parole is: " + aParole + "/100");
    	System.out.println("the number of defendants denied parole is: " + dParole + "/100");
    	System.out.println("the number of defendants who were awarded parole and will reoffend is: " + percent1R + "%");
    	System.out.println("the number of defendants who were denied parole and will not reoffend is: " + percent2R + "%");
    }
        
    public static void main(String[] args){
    	// calls all the methods 
    	defendants = createDefendants();
    	chooseCutOffs();
    	assessDefendants();
    	paroleStats();
    	
    }
	    
	
    
	



}
