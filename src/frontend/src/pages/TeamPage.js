import { React, useEffect, useState } from "react";
import { MatchDetailCard } from "../components/MatchDetailCard";
import { MatchSmallCard } from "../components/MatchSmallCard";
import { useParams } from "react-router-dom";

export const TeamPage = () => {
  const [team, setTeam] = useState({ matches: [] });
  const { teamName } = useParams();
  useEffect(() => {
    const fetchData = async () => {
      const response = await fetch(`http://localhost:8080/teams/${teamName}`);
      const data = await response.json();
      // console.log(data);
      setTeam(data);
    };
    fetchData();
  }, []);

  return team == null || team.teamName == null ? (
    <h1>No Team Found</h1>
  ) : (
    <div className="TeamPage">
      <h1>{team.teamName}</h1>
      <MatchDetailCard teamName={teamName} match={team.matches[0]} />

      {team.matches.slice(1).map((match) => (
        <MatchSmallCard key={match.id} teamName={teamName} match={match} />
      ))}
    </div>
  );
};
