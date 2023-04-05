package Golang

// CreatePhoneNumber debe crea una string "(123) 456-7890" a partir de un array de 10 enteros

import (
	"fmt"
)

func CreatePhoneNumber(numbers [10]uint) (resultado string) {
	for _, value := range numbers {
		resultado += fmt.Sprintf("%d", value)
	}
	return fmt.Sprintf("(%s) %s-%s", resultado[:3], resultado[3:6], resultado[6:])
}
