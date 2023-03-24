# Risk-Assesment-Simulation
## Details
This was an assignment done for my AP Computer Science A course 
## What is a "Risk Assesment"
Risk assessments are increasingly common in courtrooms across the nation. Courts and corrections departments around the US use algorithms to determine a defendant's "risk", which ranges from the probability that an individual will commit another crime to the likelihood a defendant will appear for his or her court date. These algorithmic scores are used to inform decisions about bail, sentencing, and parole. 

While these algorithms are seen as an “objective” way to assess defendants, the validity and equity of these scores are questionable. Typically, government agencies buy these algorithms from private businesses who often do not disclose how the algorithm makes decisions. Currently there are no federal laws that set standards or require the inspection of these tools. 

Furthermore, research studies have shown that these algorithms contain biases. For example, characteristics that are correlated with race or socioeconomic status, such as the criminal record of a person’s parents or poverty level are often used in these algorithms. Research from ProPublica has found that black and white defendants are disproportionately “mislabelled.” Blacks are more likely to be labeled “high risk” but not reoffend while whites are more likely to be labeled “low risk” but reoffend.

Read more about the potential biases and problems that can arise from risk assessments here.  

Beyond the aforementioned concerns, it is also questionable whether it is fair to make decisions on an individual case based on what similar offenders have done in the past. After all, the risk assessment scores are only probabilities and “mislabelling” cannot be avoided. Policymakers must decide how to use the scores and this will affect how many people will be “mislabelled.” 

## Simulation Detials
In this simulation, a list of 100 defendants are given an actual probability to reoffend as well as a risk assessment score based on statistics from a 2009 Ohio study on the assessment scores of inmates. 

Users are able to decide what cutoff values are used to decide which of the defendants are given parole based on their risk assessment score. The users’ choices make a big difference on incarceration and recidivism (which is the tendency for a convicted person to reoffend) rates. The user must decide how to find a balance between keeping people incarcerated who pose little threat and releasing people who may commit more crime in the future.

This simulation greatly simplifies how these algorithms are used and is not intended to represent an actual parole system. Parole boards often use these risk assessment scores in conjunction with other information.

Defendants in this simulation are up for parole. Some will reoffend if released and some won't. They each take a risk assessment, which estimates the chance they will reoffend. Before moving on, look at Five Thirty Eight’s parole assessment simulator  and read how it works. 
