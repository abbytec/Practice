function landPerimeter(arr) {
    let totlaPerimeter = 0;
    let x_length = arr.length;
    let y_length = arr[0].length;
    for (let x = 0; x < arr.length; x++) {
        for (let y = 0; y < arr[x].length; y++) {
            if (arr[x][y] == 'X') {
                totlaPerimeter += getBlockPerimeter(
                    y == 0 ? 1 : arr[x][y - 1] == 'X' ? 0 : 1,
                    x == x_length - 1 ? 1 : arr[x + 1][y] == 'X' ? 0 : 1,
                    y == y_length - 1 ? 1 : arr[x][y + 1] == 'X' ? 0 : 1,
                    x == 0 ? 1 : arr[x - 1][y] == 'X' ? 0 : 1);
            }

        }
    };
    return "Total land perimeter: " + totlaPerimeter;
}
function getBlockPerimeter(top = 0, right = 0, bottom = 0, left = 0) {
    return top + right + bottom + left;
}
