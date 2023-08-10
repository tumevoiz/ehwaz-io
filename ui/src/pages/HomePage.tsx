import React from 'react';
import {Header} from "../components/Header";
import {Category} from "../components/Category";
import {Statistics} from "../components/Statistics";
import {Footer} from "../components/Footer";
export const HomePage: React.FC = () => {
  return (
    <>
      <Header />
      <div className="container w-full py-4 flex flex-col gap-y-6">
        <Category name="First category" boards={[{
          id: "00000-0000-0000-0000-000",
          name: "First board",
          description: "First board description"
        }]} />
        <Category name="Second category" boards={[{
          id: "00000-0000-0000-0000-000",
          name: "Second board",
          description: "Second board description"
        }, {
          id: "00000-0000-0000-0000-000",
          name: "Third board",
          description: "Third board description"
        }]} />
        <Statistics />
      </div>
      <Footer />
    </>
  )
}

