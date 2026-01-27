snail = function(array) {
  let maxX=array[0].length, maxY=array.length, minX=0, minY=0;
  let remainingTotal= maxX * maxY;
  let directionX = 'r'
  let directionY = 'd'
  let finalArr = [];
  let x=0, y=0;
  while(remainingTotal > 0){
    for(;x<maxX && x>=minX&&remainingTotal > 0; directionX=='r'?x++:x--){
      finalArr.push(array[y][x]);
      remainingTotal--;
    }
    if(directionX=='r'){
      maxX--;
      x--;
      directionX='l';
    } else {
      minX++;
      x++;
      directionX='r';
    }
    directionY=='d'?y++:y--;
    for(;y<maxY && y>=minY &&remainingTotal > 0; directionY=='d'?y++:y--){
      finalArr.push(array[y][x]);
      remainingTotal--;
    }
    if(directionY=='d'){
      minY++;
      y--;
      directionY='u';
    } else {
      maxY--;
      y++;
      directionY='d';
    }
    directionX=='r'?x++:x--;
  }
  return finalArr;
}

snail([
[1, 2, 3, 4, 5, 6], 
[20, 21, 22, 23, 24, 7], 
[19, 32, 33, 34, 25, 8], 
[18, 31, 36, 35, 26, 9], 
[17, 30, 29, 28, 27, 10], 
[16, 15, 14, 13, 12, 11]
])
