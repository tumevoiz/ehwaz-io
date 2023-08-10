import React from 'react';
import {MagnifyingGlassIcon} from '@heroicons/react/24/outline';
export const Header: React.FC = () => {
  return (
    <header className="min-h-full bg-primary text-slate-200">
      <div className="md:container md:mx-auto py-2 columns-3">
        <span className="flex justify-start font-semibold text-lg">ehwaz.io</span>
        <div className="flex justify-center">
          <MagnifyingGlassIcon className="relative left-7 flex pl-2 pointer-events-none h-6 w-6 text-black" />
          <input className="block w-full pl-10 text-gray-900 border border-gray-300 rounded-lg" type="search" name="navbar-search" placeholder="Search over ehwaz.io"></input>
        </div>
        <div className="flex justify-end gap-4">
          <span className="font-medium">Messages (0)</span>
          <span className="font-bold">tumevoiz</span>
          <a href="/logout">Sign out</a>
        </div>
      </div>
    </header>

  )
}