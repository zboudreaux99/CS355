:- include('enrollment.pro').

% Rule for teaches(F, S):
teaches(F, S) :- section(Section, _, F), enrolledIn(Section, S).

% Rule for takes(S, C):
takes(S, C) :- enrolledIn(Section, S), section(Section, C, _).

teachesTwice(F, S) :-
    setof(Section, (section(Section, _, F), enrolledIn(Section, S)), Sections),
    length(Sections, N),
    N >= 2.