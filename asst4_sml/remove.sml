fun remove (x, []) = []
  | remove (x, y::ys) =
      if x = y
      then ys 
      else y :: remove(x, ys)