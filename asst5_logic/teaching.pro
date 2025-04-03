:- include('enrollment.pro').

% Rule for teaches(F, S):
teaches(F, S) :-
    section(SectionNumber, _, F),
    enrolledIn(SectionNumber, S).

% Rule for takes(S, C):
takes(S, C) :-
    section(SectionNumber, C, _),
    enrolledIn(SectionNumber, S).

% Rule for teachesTwice(F, S):
teachesTwice(F, S) :-
    findall(SectionNumber,
            (section(SectionNumber, _, F), enrolledIn(SectionNumber, S)),
            Sections),
    length(Sections, Count),
    Count >= 2.

% Redefine takes/2 to handle test queries and sort output
takes(S, C) :-
    findall(takes(St, Co), (section(SectionNumber, Co, _), enrolledIn(SectionNumber, St)), Solutions),
    sort(Solutions, SortedSolutions),  % Sort the solutions
    (   Solutions = []
    ->  write('no'), nl
    ;   print_solutions(SortedSolutions)
    ),
    fail.

print_solutions([]).
print_solutions([takes(S, C) | Rest]) :-
    write('takes('), write(S), write(', '), write(C), write(')'), nl,
    print_solutions(Rest).
