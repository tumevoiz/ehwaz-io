import React from 'react';

export const Statistics: React.FC = () => {
  return (
    <table className="w-screen border-spacing-2">
      <thead>
        <tr>
          <th className="px-4"><span className="text-xl flex justify-start">Who is online?</span></th>
          <th className="px-4 border-l-2"><span className="text-xl flex justify-start">Board statistics</span></th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>
            <span className="relative -top-4 px-4 font-light">Currently we have 0 guests and 4 logged in users.</span>
            <div className="relative -top-2 px-4">
              <a className="font-bold" href="/profile/4342-tumevoiz">tumevoiz</a>,
              <a className="font-bold" href="/profile/4342-tumevoiz">test</a>,
              <a className="font-bold" href="/profile/4342-tumevoiz">client</a>,
              <a className="font-bold" href="/profile/4342-tumevoiz">lol37</a>
            </div>
          </td>
          <td className="border-l-2 px-4">
            <ul>
              <li>0 posts</li>
              <li>0 topics</li>
              <li>0 users</li>
            </ul>
          </td>
        </tr>
      </tbody>
    </table>
  )
}