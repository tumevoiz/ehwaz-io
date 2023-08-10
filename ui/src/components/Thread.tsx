import React from 'react';

export const Thread: React.FC = () => {
  return (
    <>
      <tr>
        <td className="flex flex-col">
          <span className="text-lg font-bold">Hello world!</span>
          <div className="flex flex-row gap-1">
            <span className="font-light">by</span>
            <a className="font-bold" href="#">tumevoiz</a>
          </div>
          <span className="font-light text-sm">Started yesterday</span>
        </td>
        <td>
          <div className="flex flex-row gap-1">
            <span className="font-light">by</span>
            <a className="font-bold" href="#">anotheruser</a>
          </div>
          <span className="font-light text-sm">Today, 12:38</span>
        </td>
      </tr>
    </>
  )
}