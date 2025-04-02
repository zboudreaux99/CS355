fun insert(x: int, []: int list) = int list = [x]
  | insert (x, y::ys) =
      if x <= y 
      then x :: y :: ys
      else y :: insert(x, ys)
