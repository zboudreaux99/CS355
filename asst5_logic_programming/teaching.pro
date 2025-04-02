:- include('enrollment.pro').

% Original takes/2 rule
takes(S, C) :-
    section(SectionNumber, C, _),
    enrolledIn(SectionNumber, S).

% Helper predicate to print the takes(S, C) term
print_takes(S, C) :-
    takes(S, C),
    write('takes('), write(S), write(', '), write(C), write(')'), nl.

% Top-level predicate to drive the query and conditionally print "no"
handle_takes :-
    (   print_takes(_, _) % Attempt to print at least one takes(S, C) term
    ->  true % If successful, do nothing (backtracking is handled by test.dat)
    ;   write('no'), nl % If no takes(S, C) terms are found, print "no"
    ).

