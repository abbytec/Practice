package Golang

// CreatePhoneNumber debe crea una string a partir de un array de 10 enteros

import (
	"strconv"
)

func CreatePhoneNumber(numbers [10]uint) string {
	var miCadena string
	for i := 0; i < len(numbers); i++ {
		if i == 0 {
			miCadena = "("
		}
		miCadena = miCadena + strconv.FormatUint(uint64(numbers[i]), 10)
		if i == 2 {
			miCadena = miCadena + ") "
		}
		if i == 5 {
			miCadena = miCadena + "-"
		}
	}
	return miCadena

}
