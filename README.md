# QuantumSim

## Contributors
Dylan Bobb - dylanbobb
Jose Fernandez - joselegenda
Jorge Marcano - JorgeMarcano
Mohamed Ben Mekki - mbm64
## Inspiration
Inspired by the textbook "Quantum Computing For Computer Scientists by Noson S. Yanofsky & Mirco A. Mannucci
This project was built for the 2019 McGill physics hackathon and won the Wolfram Award.

## What it does
This program can build quantum circuits made by the user through an interactive GUI.
It also helps simulate two famous quantum algorithms (Deutchs' Algorithm, Shor's Algorithm)

## How we built it
We mainly used vanilla Java, along with the help of JavaFX for visualization/GUI.
We were able to effectively model Quantum States and Operators using matrices and kronecker products.

## Challenges we ran into
It is NP-Hard to determine if a quantum state is entangled, as such it is difficult to determine individual quantum states, even if the system is not entangled. 

## Accomplishments that we're proud of
Being able to simulate complex quantum systems (14+) qubits, and pushing our systems to their limit, truly showing the limitations of classical computers, and why quantum computers are more suited for these tasks.

## What we learned
We learnt a lot about the way that Quantum Computers work, as well as the challenges involved in programming them. While the technology/hardware is slowly becoming available, it is still unclear what problems will be efficient to solve on quantum computers. By doing this project, we developed a further understanding of the way quantum computers act, and why they may be good for some problems, but not for others.

## What's next for Quantum Computer Simulation
We plan to allow the circuit builder to allow for more complex systems, by adding more operations per qubit, more qubits, and more quantum gates. We would also like to compare our test results to actual runs on Quantum computers (e.g. IBM Q) in order to further test the effectiveness of our simulation.
