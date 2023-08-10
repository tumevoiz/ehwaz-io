import React from 'react';
import {Board, BoardQL} from "./Board";

type CategoryProps = {
  name: string
  boards: BoardQL[]
};
export const Category: React.FC<CategoryProps> = (props: CategoryProps) => {
  return (
    <table className="table-auto">
      <thead className="text-slate-200 flex px-6 py-2 bg-primary font-bold" aria-colspan={3}>{props.name}</thead>
      {props.boards.map(board =>
        <Board name={board.name} description={board.description} topicsNumber={0} postsNumber={0} />
      )}
    </table>
  )
}