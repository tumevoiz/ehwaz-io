import React from 'react';
import {Avatar} from "./Avatar";

export const Post: React.FC = () => {
  return (
    <>
      <div className="flex flex-row border-b">
        <div id="profile" className="flex flex-col items-center w-1/5 p-4">
          <span className="font-light text-xs" id="user-title">Newbie</span>
          <Avatar />
          <a className="font-bold" href="/profile/31743-tumevoiz" id="username">tumevoiz</a>
          <span className="font-light" id="user-group">Administrators</span>
          <span className="text-sm" id="user-stats">0 posts | 0 topics</span>
        </div>
        <div className="flex p-4" id="post-content">
          Hello world!
        </div>
      </div>
    </>
  )
}