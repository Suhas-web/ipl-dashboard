import { React, useEffect, useState } from "react";
import { MatchDetailCard } from "../components/MatchDetailCard";
// import { MatchSmallCard } from "../components/MatchSmallCard";
import { useParams } from "react-router-dom";

export const MatchPage = () => {
  const [matches, setMatches] = useState([]);
  const { teamName, year } = useParams();
  useEffect(() => {
    const getData = async () => {
      const response = await fetch(
        `http://localhost:8080/teams/${teamName}/matches/?year=${year}`
      );
      const data = await response.json();
      setMatches(data);
    };
    getData();
  }, []);
  return (
    <div className="MatchPage">
      {matches.map((match) => (
        <MatchDetailCard key={match.date} teamName={teamName} match={match} />
      ))}
    </div>
  );
};
