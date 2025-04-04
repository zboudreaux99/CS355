:- include('enrollment.pro').
% Rule for teaches(F, S):
teaches(F, S) :- section(Section, _, F), enrolledIn(Section, S).

% Rule for takes(S, C):
takes(S, C) :- enrolledIn(Section, S), section(Section, C, _).

% Rule for teachesTwice(F,S):
teachesTwice(F,S) :- section(Section1, _, F), enrolledIn(Section1, S), section(Section2, _, F), enrolledIn(Section2, S), Section1 \= Section2.
