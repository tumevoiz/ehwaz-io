import React from 'react';
import {UUID} from "crypto";

export type BoardQL = {
  id: UUID
  name: string
  description: string
};

type BoardProps = {
  name: string
  description: string
  topicsNumber: number
  postsNumber: number
};
export const Board: React.FC<BoardProps> = (props: BoardProps) => {
  const {
    topicsNumber = 0,
    postsNumber = 0
  } = props;
  return (
    <tr className="flex w-full">
      <td className="px-6 py-2 border flex flex-col w-3/6">
        <a className="py-1 font-semibold text-xl" href="/boards/test-board-375xah">{props.name}</a>
        <span className="font-light">{props.description}</span>
      </td>
      <td className="px-6 py-2 border flex flex-col w-1/6">
        <span className="py-1 font-light">{topicsNumber} topics</span>
        <span className="font-light">{postsNumber} posts</span>
      </td>
      <td className="px-6 py-2 border w-2/6">
        <a className="font-semibold" href="/topic/Hello-world-38a931j3">Hello world!</a><br />
        <span className="font-light text-small">Yesterday</span><br />
        <span className="text-sm">by</span> <a className="font-bold text-color-sky-500" href="/profile/3172dsa-tumevoiz">tumevoiz</a>
      </td>
    </tr>
  )
}