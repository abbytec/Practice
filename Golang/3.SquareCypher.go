package main

import (
	"math"
	"strings"
)

func Rotate(mtx [][]rune, n int, inverse bool) [][]rune {
	// Function to rotate 90 degree a rune matrix
	temp := make([][]rune, n)
	for i := 0; i < n; i++ {
		temp[i] = make([]rune, n)
	}
	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			if inverse {
				temp[i][j] = mtx[j][n-i-1]
			} else {
				temp[i][j] = mtx[n-j-1][i]
			}
		}
	}
	return temp
}

func Code(s string) string {
	// your code
	n := int(math.Ceil(math.Sqrt(float64(len(s)))))

	str := s
	for i := 0; i < (n*n)-len(s); i++ {
		str = str + "\v"
	}
	result := [][]rune{}
	for i := 0; i < len(str); i += n {
		end := i + n
		if end > len(str) {
			end = len(str)
		}
		result = append(result, []rune(str[i:end]))
	}
	temp := Rotate(result, n, false)
	text := ""
	for i, value := range temp {
		for _, value2 := range value {
			text = text + string(value2)
		}
		if i+1 < n {
			text = text + "\n"
		}
	}
	return text
}
func Decode(s string) string {
	// your code
	mtx := [][]rune{}
	arr := []rune{}
	size := 0
	for _, value := range s {
		if value != '\n' {
			arr = append(arr, value)
		} else {
			mtx = append(mtx, arr)
			size = len(arr)
			arr = []rune{}
		}
	}
	mtx = append(mtx, arr)
	mtx = Rotate(mtx, size, true)
	txt := ""
	for _, value := range mtx {
		txt = txt + string(value)
	}

	return strings.TrimRight(txt, "\v")
}
