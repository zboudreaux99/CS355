fun remove (int x, int list []) = int list []
  | remove (x, y::ys) =
      if x = y
      then ys 
      else y :: remove(x, ys)