import React from 'react';
import {Header} from "../components/Header";
import {Post} from "../components/Post";
import {Footer} from "../components/Footer";
import {ReplyBox} from "../components/ReplyBox";

export const ThreadPage: React.FC = () => {
  return (
    <>
      <Header />
        <div className="container flex w-full py-4 flex-col gap-y-6">
          <div className="flex flex-col gap-0.5">
            <span className="text-3xl font-bold">Hello world thread!</span>
            <div className="flex flex-row gap-1">
              <span className="text-lg font-light">Started by </span>
              <a className="font-bold text-lg" href="#">tumevoiz</a>
            </div>
            <span className="font-light">Yesterday</span>
          </div>
          <Post />
          <Post />
          <Post />
          <Post />
          <Post />
          <ReplyBox />
        </div>
      <Footer />
    </>
  )
}