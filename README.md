# SpellCheckAlgorithm
Java implementation of the Wagner-Fischer algorithm for minimum edit distance. Generates a list of words that are within the target edit distance. 

This implementation compares the word to a complete dictionary. Each comparison determines the edit distance between the strings using the algorithm matrix. 

I included a manual check feature that allows you to view the edit distance for any two strings with the corresponding matrix. Example, comparing boat to bat and boat to bolt:

<img width="143" alt="Screenshot 2024-06-20 at 11 49 05 PM" src="https://github.com/JosephS1618/SpellCheckAlgorithm/assets/46387707/c39964de-fb23-4397-aeb5-8f1c4f44d10f">

Some improvements have been made using an XOR operator when comparing letters, which I found reduced some mistakes with repeating letters not being accounted for as a deletion. 
I suggest using a better dictionary txt file as the one I used includes some obscure words. Additionally, through some testing, I realized the dictionary was missing some common words. 
