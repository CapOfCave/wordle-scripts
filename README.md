# wordle-scripts

This repository holds a few scripts, all based on the idea of dealing with a lot of Wordle puzzles simultaniously:

## Generate Wordle Puzzles (`puzzles`)

Ever asked yourself: What tricky wordle puzzles can I set so that there is only one solution, but with the least given information? `puzzles` contains a 'script' that helps with that question. It simulates *all* (2 × 10^11) 2-word wordle games, checks which of the games only have a single solution to the given patterns and prints them to a file at the specified path.

It also does some filtering - otherwise, the resulting file would be sevaral GBs big.

And all that in a reasonable time.


## Perfect Duotrigordle Assistant (`perfectduo`)

This part contains two scripts:

`PerfectDuoGenerator` sorts all resulting patterns by the number of possible solutions. `PerfectDuoChecker` expands on that concept by providing a way to quickly get a list of possible answers to a Wordle board. 

While these script was created to assist solving a perfect Duotrigordle, there's nothing inherently linked to Duotrigordles. As such, it can be used for all other variants of N-ordles (usablity-wise, it is optimized for big N).


## Additional information

To make the scripts perform reasonably quickly, this projects includes a highly performance-optimized wordle evaluator. It uses bitwise operations to represent a Wordle evaluation as an integer. By making the evaluation results compact, they cover a continuous interval of minimum size, the solution can later be used to index an array. This leads to performant O(n) lookups of solution -> word.

