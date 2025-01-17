package org.finos.waltz.model.bulk_upload.legal_entity_relationship;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.finos.waltz.model.EntityReference;
import org.immutables.value.Value;

import java.util.Map;
import java.util.Optional;

@Value.Immutable
@JsonSerialize(as = ImmutableResolvedReference.class)
@JsonDeserialize(as = ImmutableResolvedReference.class)
public abstract class ResolvedReference {

    public abstract String inputString();

    public abstract Optional<EntityReference> resolvedEntityReference();


    public static ResolvedReference mkResolvedReference(String inputString, Map<String, EntityReference> identifierLookupMap) {
        EntityReference reference = identifierLookupMap.get(inputString);
        return ImmutableResolvedReference.builder()
                .inputString(inputString)
                .resolvedEntityReference(Optional.ofNullable(reference))
                .build();
    }

}
