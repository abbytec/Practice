package Java.cw;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MarpleSolver {

    public static String solve(String[] clues) {
        // Your Code Here

        class Solution {
            private Set<Character> thisClue;
            private Set<Character> nextTo;
            private Solution myLeft;
            private Solution myRight;

            public Solution(Set<Character> thisClue, Solution myLeft, Solution myRight) {
                this.thisClue = thisClue;
                this.myLeft = myLeft;
                this.myRight = myRight;
                this.nextTo = new HashSet<>();
            }

            public Solution(Set<Character> thisClue, Solution myLeft, Solution myRight, Set<Character> nextTo) {
                this.thisClue = thisClue;
                this.myLeft = myLeft;
                this.myRight = myRight;
                this.nextTo = new HashSet<>();
            }

            public Set<Character> getThisClue() {
                return this.thisClue;
            }

            public Solution getMyLeft() {
                return this.myLeft;
            }

            public Solution getMyRight() {
                return this.myRight;
            }

            public Set<Character> getNextTo() {
                return nextTo;
            }

            public void setThisClue(Set<Character> thisClue) {
                this.thisClue = thisClue;
            }

            public void setMyRight(Solution myRight) {
                this.myRight = myRight;
            }

            public void setMyLeft(Solution myLeft) {
                this.myLeft = myLeft;
            }

            public void setNextTo(Set<Character> nextTo) {
                this.nextTo = nextTo;
            }

            public static void mergeSolutions(Solution solution0, Solution solution2,
                    Map<Character, Solution> solutionMap) {
                solution0.thisClue.addAll(solution2.thisClue);
                if (solution2.myLeft != null) {
                    solution0.setMyLeft(solution2.myLeft);
                }
                if (solution2.myRight != null) {
                    solution0.setMyRight(solution2.myRight);
                }
                for (Character character : solution2.thisClue) {
                    solutionMap.put(character, solution0);
                }
            }

            public static List<String> tryResolveClues(List<String> clues, Map<Character, Solution> solutionMap) {
                LinkedList<String> restOfClues = new LinkedList<>();
                for (String clue : clues) {
                    Solution solution0 = solutionMap.get(clue.charAt(0));

                    if (clue.charAt(0) == clue.charAt(2)) {
                        // [0] esta al lado de [1]
                        Solution solution1 = solutionMap.get(clue.charAt(1));
                        if (solution0 == null && solution1 == null) {
                            restOfClues.addFirst(clue);
                        } else {
                            if (solution0 != null) {
                                solution0.getNextTo().add(clue.charAt(1));
                            }
                            if (solution1 != null) {
                                solution1.getNextTo().add(clue.charAt(0));
                            }
                        }

                    } else {
                        Solution solution2 = solutionMap.get(clue.charAt(2));
                        if (solution0 == null && solution2 == null) {
                            restOfClues.addFirst(clue);
                        } else {
                            if (clue.charAt(1) == '^') {
                                // [0] y [2] are on same column
                                if (solution0 == null) {
                                    solution2.thisClue.add(clue.charAt(0));
                                    solutionMap.put(clue.charAt(0), solution2);
                                } else if (solution2 == null) {
                                    solution0.thisClue.add(clue.charAt(2));
                                    solutionMap.put(clue.charAt(2), solution0);
                                } else if (solution0 != solution2) {
                                    Solution.mergeSolutions(solution0, solution2, solutionMap);
                                }
                            } else if (clue.indexOf('<') >= 0) {
                                // [0] is at left of [2]
                                if (solution0 == null) {
                                    Set<Character> hashSet = new HashSet<Character>();
                                    hashSet.add(clue.charAt(0));
                                    solution0 = new Solution(hashSet, null, solution2);
                                    solutionMap.put(clue.charAt(0), solution0);
                                    solution2.setMyLeft(solution0);
                                } else if (solution2 == null) {
                                    Set<Character> hashSet = new HashSet<Character>();
                                    hashSet.add(clue.charAt(2));
                                    solution2 = new Solution(hashSet, solution0, null);
                                    solutionMap.put(clue.charAt(2), solution2);
                                    solution0.setMyRight(solution2);
                                } else {
                                    if (solution0.myRight == null) {
                                        solution0.setMyRight(solution2);
                                    } else {
                                        Solution.mergeSolutions(solution0.myRight, solution2, solutionMap);
                                    }
                                    if (solution2.myLeft == null) {
                                        solution2.setMyLeft(solution0);
                                    } else {
                                        Solution.mergeSolutions(solution2.myLeft, solution0, solutionMap);
                                    }
                                }
                            } else {
                                // [1] esta entre [0] y [2]
                                Solution solution1 = solutionMap.get(clue.charAt(1));
                                if (solution1 == null) {
                                    restOfClues.addFirst(clue);
                                } else {
                                    if (solution0.myRight == solution1) {
                                        solution1.myRight = solution2;
                                        solution2.myLeft = solution1;
                                    } else if (solution0.myLeft == solution1) {
                                        solution1.myLeft = solution2;
                                        solution2.myRight = solution1;
                                    } else if (solution2.myLeft == solution1) {
                                        solution1.myLeft = solution0;
                                        solution0.myRight = solution1;
                                    } else if (solution2.myRight == solution1) {
                                        solution1.myRight = solution0;
                                        solution0.myLeft = solution1;
                                    } else {
                                        solution1.getNextTo().add(clue.charAt(0));
                                        solution0.getNextTo().add(clue.charAt(1));
                                        solution1.getNextTo().add(clue.charAt(2));
                                        solution2.getNextTo().add(clue.charAt(1));
                                    }

                                }
                            }
                        }
                    }
                }

                Set<Solution> checkedSolutions = new HashSet<>();
                solutionMap.forEach((character, solution) -> {
                    if (!checkedSolutions.contains(solution)) {
                        checkedSolutions.add(solution);
                        if (solution.myLeft != null) {
                            if (solution.myRight != null) {
                                Set<Character> intersection = new HashSet<>(solution.myLeft.getThisClue());
                                intersection.retainAll(solution.myRight.getThisClue());
                                if (intersection.size() > 0) {
                                    solution.thisClue.addAll(intersection);
                                    for (Character inter : intersection) {
                                        solutionMap.put(inter, solution);
                                    }
                                }
                            }
                        }
                    }
                });
                return restOfClues;
            }
        }

        // Original clues
        Map<Character, Solution> solutionMap = new HashMap<>();

        // contar la cantidad de veces que aparece cada carácter en la lista de cadenas
        Set<Character> firstSet, secondSet, thirdSet;
        boolean firstSetted = false;
        for (String s : clues) {
            if (s.charAt(0) != s.charAt(1) && s.charAt(0) != s.charAt(2) && s.charAt(1) != s.charAt(2)
                    && s.charAt(1) != '^' && s.charAt(1) != '^' && !firstSetted) {
                firstSetted = true;

                firstSet = new HashSet<>();
                firstSet.add(s.charAt(0));

                secondSet = new HashSet<>();
                secondSet.add(s.charAt(1));

                thirdSet = new HashSet<>();
                thirdSet.add(s.charAt(2));

                solutionMap.put(s.charAt(0), new Solution(firstSet, null, null, secondSet));
                solutionMap.put(s.charAt(2), new Solution(thirdSet, null, null, secondSet));
                firstSet.addAll(secondSet);
                solutionMap.put(s.charAt(1), new Solution(secondSet, null, null, firstSet));

            }
        }

        List<String> clues2 = Arrays.asList(clues);
        Set<String> clueSet = new HashSet<>();
        while (clues2.size() > 0) {
            clues2 = Solution.tryResolveClues(clues2, solutionMap);
            /*
             * solutionMap.forEach((character, solution) -> {
             * if (solution.getNextTo().size() > 0) {
             * for (Character cha : solution.getNextTo()) {
             * for (Character cha2 : solution.getThisClue()) {
             * if (cha < cha2) {
             * clueSet.add(cha + cha2 + cha + "");
             * } else {
             * clueSet.add(cha2 + cha + cha2 + "");
             * }
             * }
             * }
             * }
             * });
             */
            System.out.println(clues2);
        }

        Character[][] rows = new Character[][] { { 'A', 'B', 'C', 'D', 'E' }, { 'F', 'G', 'H', 'I', 'J' },
                { 'K', 'L', 'M', 'N', 'O' }, { 'P', 'Q', 'R', 'S', 'T' } };

        boolean leftFound = false;
        Solution leftMost = solutionMap.get('A');
        while (!leftFound) {
            if (leftMost.myLeft != null) {
                leftMost = leftMost.myLeft;
            } else {
                leftFound = true;
            }
        }

        List<Set<Character>> solutions = new ArrayList<>();
        while (leftMost.myRight != null) {
            solutions.add(leftMost.getThisClue());
            leftMost = leftMost.myRight;
        }
        System.out.println(solutions);

        StringBuilder sb = new StringBuilder();

        for (int col = 0; col < 5; col++) {
            for (int row = 0; row < 4; row++) {
                if (solutions.get(col).contains(rows[row][col])) {
                    sb.append(rows[row][col]);
                }
            }
        }
        return sb.toString();

    }

    public static void main(String[] args) {
        String[] clues = new String[] {
                "MRT", "ABH", "LKO", "OKP", "JIM", "OPE", "GDO", "RAQ", "J^A", "M^P", "A<Q", "D<K", "OQO"
        };
        System.out.println(solve(clues));
    }
}