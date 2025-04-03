:- include('enrollment.pro').

% Rule for teaches(F, S):
teaches(F, S) :- section(Section, _, F), enrolledIn(SectionNumber, S).

% Rule for takes(S, C):
takes(S, C) :- section(Section, C, S), enrolledIn(SectionNumber, S).
