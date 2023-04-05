package kata

import (
	"regexp"
	"unicode"
)

// Complete the method/function so that it converts dash/underscore delimited words into camel casing.
func ToCamelCase(s string) string {
	// your code
	splitted := regexp.MustCompile(`[-_]+`).Split(s, -1)
	var text string
	for i := 0; i < len(splitted); i++ {
		chars := []rune(splitted[i])
		for j := 0; j < len(chars); j++ {
			if j == 0 {
				if i != 0 {
					chars[j] = unicode.ToUpper(chars[j])
				}
			} else {
				chars[j] = unicode.ToLower(chars[j])
			}
		}

		text = text + string(chars)
	}
	return text
}
