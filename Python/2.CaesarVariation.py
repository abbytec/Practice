from math import ceil


class Translation:
    def __init__(self):
        self.alphabet = "abcdefghijklmnopqrstuvwxyz"
        self.alphabet2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

    def translate(self, text, shift):
        finalShift = shift % 26
        newText = text.translate("".maketrans(
            self.alphabet, self.alphabet[finalShift:] + self.alphabet[:finalShift]))
        newText = newText.translate("".maketrans(
            self.alphabet2, self.alphabet2[finalShift:] + self.alphabet2[:finalShift]))
        return newText

    def getShift(self, startChar, finalChar):
        i1 = self.alphabet.find(startChar)
        i2 = self.alphabet.find(finalChar)
        diff = i2 - i1
        if (diff < 0):
            diff = diff + 26
        return diff


def encode_str(strng, shift):
    # your code
    translator = Translation()
    prefix = strng[0:1].lower()
    newStrng = prefix + \
        translator.translate(prefix, shift) + \
        translator.translate(strng, shift)
    divided_by_five = ceil((len(strng)+2)/5)
    if ((len(strng)+2)/divided_by_five <= divided_by_five):
        worker_size = divided_by_five
        size = 5
    else:
        worker_size = round((len(strng)+2)/4)
        size = 4

    workers = []
    for i in range(size):
        if (worker_size*(i+1) < len(newStrng)):
            workers.append(newStrng[(worker_size*i):(worker_size*(i+1))])
        else:
            if (newStrng[(worker_size*i):(worker_size*(i+1))] != ''):
                workers.append(newStrng[worker_size*i:])
    return workers


def decode(arr):
    # your code
    translator = Translation()
    fullStr = "".join(arr)
    shift = translator.getShift(fullStr[0:1], fullStr[1:2])
    return translator.translate(fullStr[2:], -shift)
