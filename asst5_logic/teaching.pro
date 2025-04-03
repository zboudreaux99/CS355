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
    (   \+ (section(_, _, _), enrolledIn(_, _))  % Check if ANY solutions exist
    ->  write('no'), nl, fail  % If no solutions, print "no" and fail
    ;   findall(takes(St, Co), (takes(St, Co)), Solutions),  % Otherwise, find all solutions
        sort(Solutions, SortedSolutions),  % Sort the solutions
        print_solutions(SortedSolutions),  % Print the solutions
        fail  % Force backtracking
    ).

print_solutions([]).
print_solutions([takes(S, C) | Rest]) :-
    write('takes('), write(S), write(', '), write(C), write(')'), nl,
    print_solutions(Rest).
