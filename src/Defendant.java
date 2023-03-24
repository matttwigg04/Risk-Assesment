// STUDENT VERSION

// Defendant.java
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

public class Defendant{
    private double trueRisk;
    private boolean willReoffend;
    private double assessmentScore;
    private String riskCategory;
    private boolean givenParole;

    Defendant(double risk, boolean reoffend){
	// constructor
	trueRisk = risk;
	willReoffend = reoffend;
	assessmentScore = 0;
	riskCategory = null;
	givenParole = false;
    }

    public String toString(){
	// toString
	return "Assessment Score: " + this.assessmentScore + '\n' +
	    "Risk Category: " + this.riskCategory + '\n' +
	    "Will Reoffend: " + this.willReoffend + '\n' +
	    "Given Parole: " + this.givenParole;
    }

    // accessor methods

    public double getTrueRisk(){ return trueRisk; }
    public boolean willOffendAgain() { return willReoffend; }
    public double getAssessmentScore() { return assessmentScore; }
    public String getRiskCategory() { return riskCategory; }
    public boolean isGivenParole() { return givenParole; }

    // mutator methods

    public void assess(double low, double med, double high){
	// gives assessment score based on trueRisk and assigns riskCat
    //57 - 58 calculate the defendats trueRisk Score
    	Random rand = new Random();
    	assessmentScore = (((rand.nextGaussian()) * 0.15) + trueRisk);

    	//The following if statements assign an a risk Category to the defendant based on their assessment score
    	if (assessmentScore > low) {
    		if (assessmentScore > med ) {
    			riskCategory = "high";
        	}
    		else riskCategory = "med";
    	}
    	else 
    		riskCategory = "low";
	}

    public void decideParole(){
	// based on the risk category determine parole
    	
    	double random = Math.random();// random is randomly given a number anywhere between 0.0 and 1.0
    	// the following two if statements either award the defendant parole or deny the defendant parole based on their risk category
    	if (riskCategory == "high") {
    		givenParole = false;
    	}
    	if (riskCategory == "low") {
    		givenParole = true;
    	}
    	//since the risk is med, the defendant will be awarded a or denied parole on a 50 50 chance. if random is less than .5 they will be awarded parole, if random is greater then .5 they will be denied parole
    	if (riskCategory == "med") {
    		if (random < 0.5) {
    			givenParole = true;
    		}
    		if (random > 0.5) {
    			givenParole = false;
    		}
    		
    	}
    }
}
	
		



