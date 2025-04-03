:- include('enrollment.pro').

% Rule for teaches(F, S):
teaches(F, S) :- section(Section, _, F), enrolledIn(Section, S).

% Rule for takes(S, C):
takes(S, C) :- enrolledIn(Section, S), section(Section, C, _).

teachesTwice(F, S) :-
    enrolledIn(Section1, S),  % Find a section where S is enrolled
    enrolledIn(Section2, S),  % Find another section where S is enrolled
    Section1 \= Section2,     % Ensure they are different sections
    section(Section1, _, F),  % Find the faculty F for Section1
    section(Section2, _, F).  % Find the faculty F for Section2