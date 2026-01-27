const Direction = {
  Point: -1,
  Vert: 0,
  Hor: 1,
  Wrong: 'x',
  None: 'n'
};

function checkDirection(x,y,field,elements){
  let direction = Direction.None;
  let startY=(y==0?0:y-1),startX=(x==0?0:x-1), endY=(y==9?9:y+1), endX=(x==9?9:x+1);
  let shortY=(endY-startY)<2, shortX=(endX-startX)<2;
  for(let tempY=startY; tempY<=endY; tempY++){
    for(let tempX=startX; tempX<=endX; tempX++){
      if(field[tempY][tempX] == 1 && !( x == tempX && y == tempY)){
        if (
          ((shortX && tempX != x) || (!shortX)) &&
          ((shortY && tempY == y) || (tempY != startY && tempY != endY)) &&
          (direction != Direction.Vert)) {
          direction = Direction.Hor;
        } else if (
          ((shortY && tempY != y) || (!shortY)) &&
          ((shortX && tempX == x) || (tempX != startX && tempX != endX)) &&
          (direction != Direction.Hor)) {
          direction = Direction.Vert;
        } else {
          direction = Direction.Wrong;
        }
      } else if (direction == Direction.None) {
        direction = Direction.Point;
      }
    }
  }
  let count = 0;
  if (direction == Direction.Vert) {
    count = shortY?0:1;
    for (let tempY = endY; tempY < 10; tempY++){
      if (field[tempY][x] == 1) {
        count++;
      } else {
        break;
      }
    }
    for (let tempY = startY; tempY >= 0; tempY--){
      if (field[tempY][x] == 1) {
        count++;
      } else {
        break;
      }
    }
  } else if (direction == Direction.Hor) {
    count = shortX?0:1;
    for (let tempX = endX; tempX < 10; tempX++){
      if (field[y][tempX] == 1) {
        count++;
      } else {
        break;
      }
    }
    for (let tempX = startX; tempX >= 0; tempX--){
      if (field[y][tempX] == 1) {
        count++;
      } else {
        break;
      }
    }
  } else if (direction == Direction.Point) {
    count = 1;
  }
  switch (count) {
    case 4:
      elements.bs++;
      break;
    case 3:
      elements.cr++;
      break;
    case 2:
      elements.ds++;
      break;
    case 1:
      elements.sm++;
      break;
    default:
      direction = Direction.Wrong;
      break;
  }

  return direction != Direction.Wrong;
}


function validateBattlefield(field) {
  let elements = {
    bs: 0,
    cr: 0,
    ds: 0,
    sm: 0,
  }
  let isValid = true;
  for(let y=0; y<10; y++){
    for(let x=0; x<10; x++){
      if(isValid && field[y][x] == 1){
        isValid = checkDirection(x,y,field, elements);
      }
    }
  }
  elements.bs = elements.bs / 4;
  elements.cr = elements.cr / 3;
  elements.ds = elements.ds / 2;
  return isValid && elements.bs == 1 && elements.cr == 2 && elements.ds == 3 && elements.sm == 4;
}

console.log(validateBattlefield(
    [
            [1, 0, 0, 0, 0, 1, 1, 0, 0, 0],
            [1, 0, 1, 0, 0, 0, 0, 0, 1, 0],
            [1, 0, 1, 0, 1, 1, 1, 0, 1, 0],
            [1, 0, 0, 0, 0, 0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0, 0, 0, 0, 1, 0],
            [0, 0, 0, 0, 1, 1, 1, 0, 0, 0],
            [0, 0, 0, 0, 0, 0, 0, 0, 1, 0],
            [0, 0, 0, 1, 0, 0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0, 0, 0, 1, 0, 0],
            [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
        ]
)); 
console.log(validateBattlefield([
            [1,0,0,0,0,1,1,0,0,0],
            [1,0,1,0,0,0,0,0,1,0],
            [1,0,1,0,1,1,1,0,1,0],
            [1,0,0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0,1,0],
            [0,0,0,0,1,1,1,0,0,0],
            [0,0,0,1,0,0,0,0,1,0],
            [0,0,0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,1,0,0],
            [0,0,0,0,0,0,0,0,0,0]
]));