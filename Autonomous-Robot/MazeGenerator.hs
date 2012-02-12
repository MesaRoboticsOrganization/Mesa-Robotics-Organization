----------------------------------------------------
-- |
--
-- Maintainer :  Kiet Lam <ktklam9@gmail.com>
--
--
-- This module will generate a random maze
--
-- Note: Not finished, need to create maze using
-- recursive division
--
--
----------------------------------------------------

module MazeGenerator (
  ) where

import System.Random


data Location = TopLeft
              | TopRight
              | BottomLeft
              | BottomRight
              deriving (Show, Enum)


data Direction = North
               | South
               | East
               | West
               deriving (Show, Enum)


data Block = Block
           | Hole
           deriving (Show)


data Wall = Wall [Block] Direction
          deriving (Show)


data Room = EmptyRoom
          | Room Wall Wall Wall Wall Location [Room]
          deriving (Show)


replaceElem :: Int -> a -> [a] -> [a]
replaceElem _ _ [] = []
replaceElem 0 x (_:ys) = x:ys
replaceElem n x (y:ys) = y:(replaceElem (n - 1) x ys)


createWall :: Int -> Direction -> Wall
createWall nBlocks direction =
  Wall (replicate nBlocks Block) direction


punchRandomHole :: StdGen -> Wall -> Wall
punchRandomHole gen (Wall bs dir) =
  let (ranNum, _) = randomR (0, length bs) gen
      bs' = replaceElem ranNum Hole bs
  in
   Wall bs' dir


createRoom :: Int -> Int -> Location -> Room
createRoom length width loc =
  Room (createWall length North) (createWall length South) (createWall width East) (createWall width West) loc []


getMaxDimensions :: Room -> (Int, Int)
getMaxDimensions EmptyRoom = (0, 0)
getMaxDimensions (Room n s e w _ _) =
  let f = \(Wall bs1 _) (Wall bs2 _) -> max (length bs1) (length bs2) in
  (f n s, f e w)


divideRoomToFour :: Room -> Int -> Int -> [Room]
divideRoomToFour EmptyRoom _ _ = []
divideRoomToFour r@(Room n s e w _ _) splitL splitW =
  let (l, w) = getMaxDimensions r in
  [createRoom splitL splitW TopLeft, createRoom (l - splitL) splitW BottomLeft,
   createRoom splitL (w - splitW) TopRight, createRoom (l - splitL) (w - splitW) BottomRight]


-- createMaze :: StdGen -> Int -> Int -> Int -> Int -> Room
-- createMaze gen initLen initWid minLen minWid
--   | initLen < minLen || initWid < minWid = EmptyRoom
--   | otherwise =
