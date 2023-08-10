import React from 'react';
import {Header} from "../components/Header";
import {Category} from "../components/Category";
import {Statistics} from "../components/Statistics";
import {Footer} from "../components/Footer";
import {Thread} from "../components/Thread";

export const BoardPage: React.FC = () => {
  return (
    <>
      <Header />
      <div className="container w-full py-4 flex flex-col gap-y-6">
        <table className="table-auto">
          <thead>
            <tr>
              <th className="w-3/4">Topic</th>
              <th className="w-1/4">Last post</th>
            </tr>
          </thead>
          <tbody>
            <Thread />
            <Thread />
            <Thread />
            <Thread />
          </tbody>
        </table>
      </div>
      <Footer />
    </>
  )
}