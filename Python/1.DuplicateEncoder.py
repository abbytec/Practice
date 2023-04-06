# convert a string to a new string where each character in the new string is "(" if that character appears only once in the original string, or ")" if that character appears more than once in the original string. Ignore capitalization when determining if a character is a duplicate.
def duplicate_encode(word):
    # your code here
    newWord = ''
    for character in word.lower():
        if (word.lower().count(character) > 1):
            newWord += ")"
        else:
            newWord += "("
    return newWord
