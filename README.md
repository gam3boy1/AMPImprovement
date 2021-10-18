# AMP Improvement Program

## Software Contribution
For our software contribution, we have designed a program that takes an AMP sequence as input, as well as other relevant information, and outputs all near-optimal variations of the inputted peptide sequence based on amino acid properties and their correlating positions. 

## Peptides
For peptides that possess a beta-sheet structure, inputs including vital structure positions and turning amino acid positions are required. After the input is made, each amino acid within the peptide is classified according to its properties and effect on the peptide’s antimicrobial ability. The classification is then reordered in order to optimize the peptide based on an ideal alternating amphipathic sequence which was researched with great detail [here](https://www.sciencedirect.com/science/article/abs/pii/S1748013221001547?dgcid=rss_sd_all).
	  
 ## Output
The output file is titled out.FASTA will contain a high quantity of generated peptide sequences. These peptides are variants of the base peptide. Each variant will possess the indicated amount of residue mutations. Out.FASTA is designed to be used in conjunction with the AxPEP sequence-based machine learning models, specifically with the RF-AmPEP30 model. This machine learning model can take massive quantities of peptide sequences and output a predicted peptide score based on their data. The machine learning model may be found [here](https://app.cbbio.online/ampep/home).

## How to Run the Program
1. Download the github repository
2. Open terminal (for MacOS) / command line (for Windows)
3. Open the folder on your console
- On both macOS and Windows type: `cd (your directory)/AMPImprovement-main/AMP_Improvement`
4. Compile and run the program (must have java installed on your computer - download from [here](https://www.oracle.com/java/technologies/downloads/))
	- On both macOS and Windows type:
	```
	javac *.java
	java Main.java
	```
Once you have completed the above steps you should see the program launch with on screen instructions. 
